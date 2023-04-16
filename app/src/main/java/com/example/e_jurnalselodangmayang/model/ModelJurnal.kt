package com.example.e_jurnalselodangmayang.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ModelJurnal (
    var judulJurnal : String? = null,
    var tahunterbit : String? = null
) :Parcelable