package dev.jiskelo.mornhouse.presenter.second

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import dev.jiskelo.mornhouse.util.Constants
import dev.jiskelo.mornhouse.util.jsonToEntity

class SecondViewModel : ViewModel() {

    fun checkArguments(arg : Bundle?) :NumberEntity? {
        val numberEntityJson = arg?.getString(Constants.KEY_BUNDLE_NUMBER)
        return numberEntityJson?.jsonToEntity()
    }
}