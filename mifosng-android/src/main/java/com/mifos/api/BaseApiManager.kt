/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.api

import com.google.gson.GsonBuilder
import com.mifos.api.services.AuthService
import com.mifos.api.services.CenterService
import com.mifos.api.services.ChargeService
import com.mifos.api.services.CheckerInboxService
import com.mifos.api.services.ClientAccountsService
import com.mifos.api.services.ClientService
import com.mifos.api.services.CollectionSheetService
import com.mifos.api.services.DataTableService
import com.mifos.api.services.DocumentService
import com.mifos.api.services.GroupService
import com.mifos.api.services.LoanService
import com.mifos.api.services.NoteService
import com.mifos.api.services.OfficeService
import com.mifos.api.services.RunReportsService
import com.mifos.api.services.SavingsAccountService
import com.mifos.api.services.SearchService
import com.mifos.api.services.StaffService
import com.mifos.api.services.SurveyService
import com.mifos.utils.JsonDateSerializer
import com.mifos.utils.PrefManager.instanceUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.Date

/**
 * @author fomenkoo
 */
class BaseApiManager {
    init {
        createService()
    }

    val authApi: AuthService
        get() = Companion.authApi
    val centerApi: CenterService
        get() = Companion.centerApi
    val accountsApi: ClientAccountsService
        get() = Companion.accountsApi
    val clientsApi: ClientService
        get() = Companion.clientsApi
    val dataTableApi: DataTableService
        get() = Companion.dataTableApi
    val loanApi: LoanService
        get() = Companion.loanApi
    val savingsApi: SavingsAccountService
        get() = Companion.savingsApi
    val searchApi: SearchService
        get() = Companion.searchApi
    val groupApi: GroupService
        get() = Companion.groupApi
    val documentApi: DocumentService
        get() = Companion.documentApi
    val officeApi: OfficeService
        get() = Companion.officeApi
    val staffApi: StaffService
        get() = Companion.staffApi
    val surveyApi: SurveyService
        get() = Companion.surveyApi
    val chargeApi: ChargeService
        get() = Companion.chargeApi
    val checkerInboxApi: CheckerInboxService
        get() = Companion.checkerInboxApi
    val collectionSheetApi: CollectionSheetService
        get() = Companion.collectionSheetApi
    val noteApi: NoteService
        get() = Companion.noteApi
    val runReportsService: RunReportsService
        get() = Companion.runReportsService

    companion object {
        private var mRetrofit: Retrofit? = null
        private lateinit var authApi: AuthService
        private lateinit var centerApi: CenterService
        private lateinit var accountsApi: ClientAccountsService
        private lateinit var clientsApi: ClientService
        private lateinit var dataTableApi: DataTableService
        private lateinit var loanApi: LoanService
        private lateinit var savingsApi: SavingsAccountService
        private lateinit var chargeApi: ChargeService
        private lateinit var searchApi: SearchService
        private lateinit var groupApi: GroupService
        private lateinit var documentApi: DocumentService
        private lateinit var officeApi: OfficeService
        private lateinit var staffApi: StaffService
        private lateinit var surveyApi: SurveyService
        private lateinit var runReportsService: RunReportsService
        private lateinit var noteApi: NoteService
        private lateinit var collectionSheetApi: CollectionSheetService
        private lateinit var checkerInboxApi: CheckerInboxService

        fun init() {
            authApi = createApi(
                AuthService::class.java
            )
            centerApi = createApi(
                CenterService::class.java
            )
            accountsApi = createApi(
                ClientAccountsService::class.java
            )
            clientsApi = createApi(
                ClientService::class.java
            )
            dataTableApi = createApi(
                DataTableService::class.java
            )
            loanApi = createApi(
                LoanService::class.java
            )
            savingsApi = createApi(
                SavingsAccountService::class.java
            )
            searchApi = createApi(
                SearchService::class.java
            )
            groupApi = createApi(
                GroupService::class.java
            )
            documentApi = createApi(
                DocumentService::class.java
            )
            officeApi = createApi(
                OfficeService::class.java
            )
            staffApi = createApi(
                StaffService::class.java
            )
            surveyApi = createApi(
                SurveyService::class.java
            )
            chargeApi = createApi(
                ChargeService::class.java
            )
            runReportsService = createApi(
                RunReportsService::class.java
            )
            noteApi = createApi(
                NoteService::class.java
            )
            collectionSheetApi = createApi(
                CollectionSheetService::class.java
            )
            checkerInboxApi = createApi(
                CheckerInboxService::class.java
            )
        }

        private fun <T> createApi(clazz: Class<T>): T {
            return mRetrofit!!.create(clazz)
        }

        fun createService() {
            val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, JsonDateSerializer()).create()
            mRetrofit = Retrofit.Builder()
                .baseUrl(instanceUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MifosOkHttpClient().mifosOkHttpClient)
                .build()
            init()
        }
    }
}