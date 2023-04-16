package com.example.e_jurnalselodangmayang

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.e_jurnalselodangmayang.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // signup option google account
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.tvSwregister.setOnClickListener {
            binding.tvSwregister.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.tvSwregister.setTextColor(resources.getColor(R.color.white, null))
            binding.tvSwlogin.background = null
            binding.llRegister.visibility = View.VISIBLE
            binding.llLogin.visibility = View.GONE
            binding.tvSwlogin.setTextColor(resources.getColor(R.color.blue_color, null))
            binding.ivLogin.visibility = View.GONE
            binding.btnLogin.visibility = View.GONE
            binding.btnRegister.visibility = View.VISIBLE
        }

        binding.tvSwlogin.setOnClickListener {
            binding.tvSwregister.background = null
            binding.tvSwregister.setTextColor(resources.getColor(R.color.blue_color, null))
            binding.tvSwlogin.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.llRegister.visibility = View.GONE
            binding.llLogin.visibility = View.VISIBLE
            binding.ivLogin.visibility = View.VISIBLE
            binding.tvSwlogin.setTextColor(resources.getColor(R.color.white, null))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

        binding.btnGoogleaccount.setOnClickListener {
            signInGoogle()
        }

        binding.btnRegister.setOnClickListener {

            val name =binding.etNameregister.text.toString()
            val username = binding.etUsernameregister.text.toString()
            val email = binding.etEmailregister.text.toString()
            val contact = binding.etContactregister.text.toString()
            val password = binding.etPasswordregister.text.toString()

            // validasi form
            if (name.isEmpty()) {
                binding.etNameregister.error = "Nama harus diisi"
                binding.etNameregister.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.etUsernameregister.error = "Username harus diisi"
                binding.etUsernameregister.requestFocus()
                return@setOnClickListener
            }

            if (contact.isEmpty()) {
                binding.etContactregister.error = "Contact harus diisi"
                binding.etContactregister.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.PHONE.matcher(contact).matches()) {
                binding.etContactregister.error = "Contact tidak valid"
                binding.etContactregister.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etEmailregister.error = "Email harus diisi"
                binding.etEmailregister.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailregister.error = "Email tidak valid"
                binding.etEmailregister.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPasswordregister.error = "Password harus diisi"
                binding.etPasswordregister.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.etPasswordregister.error = "Password harus lebih dari 6 karakter"
                binding.etPasswordregister.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account : GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)  // belum pasti
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT)   .show()
                }
            }
    }
}