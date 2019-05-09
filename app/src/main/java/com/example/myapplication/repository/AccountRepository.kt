package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.dao.AppDatabase
import com.example.myapplication.model.database.AccountModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

class AccountRepository(context: Context){
    private val appDatabase = AppDatabase.getInstance(context)

    interface ResultListener<ResultType>{
        fun onResult(data: ResultType)
    }

    fun insertAccount(accountData: AccountModel){
        doAsync {
            appDatabase.accountDao().insertAccount(accountData)
        }
    }

    fun getAccount(username:String, resultListener: ResultListener<AccountModel>){
        doAsync {
            val account = appDatabase.accountDao().getAccount(username)

            onComplete {
                resultListener.onResult(account)
            }
        }
    }
}