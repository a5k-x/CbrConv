package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.repository.Repository
import retrofit2.Call

class MainInteractor(
    private val remoteDataModel: Repository<DataModel>,
    private val localDataModel: Repository<DataModel>
) : Interactor<DataModel> {

    override fun getData(checkBool: Boolean): Call<DataModel> {
        return if (checkBool) {
            remoteDataModel.getData()
        } else localDataModel.getData()
    }

}