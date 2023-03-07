package com.example.noname.view.users

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.telecom.ConnectionService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.FailureGetUsers
import com.example.domain.SuccessGetUsers
import com.example.domain.model.User
import com.example.domain.usecase.GetSavedUsersUseCase
import com.example.domain.usecase.SaveUserUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.noname.R
import com.example.noname.USERS_KEY
import com.example.noname.adapter.UsersAdapter
import com.example.noname.databinding.FragmentAllUsersBinding
import com.example.noname.view.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass

class AllUsersFragment : BaseFragment<FragmentAllUsersBinding>() {
    private lateinit var adapter: UsersAdapter

    private val saveUser: SaveUserUseCase by inject()
    private val getUsers: GetUsersUseCase by inject()
    private val getSavedUsers: GetSavedUsersUseCase by inject()

    private var users: List<User>? = null

    override val binder: (LayoutInflater, ViewGroup?) -> FragmentAllUsersBinding =
        { layoutInflater, container ->
            FragmentAllUsersBinding.inflate(layoutInflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("edz. onViewCreated(). savedInstanceState - $savedInstanceState")

        lifecycleScope.launch {
            binding.progressUsers.isVisible = true

            if (savedInstanceState != null) {
                users = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    savedInstanceState.getParcelable(USERS_KEY, listOf<User>().javaClass)
                } else {
                    savedInstanceState.getParcelableArrayList(USERS_KEY)
                }
            } else {
                when (val usersResult = getUsers()) {
                    is SuccessGetUsers -> {
                        val userIds = getSavedUsers.getUserIds()

                        users = usersResult.value.map { user ->
                            if (userIds.contains(element = user.id.toLong())) {
                                user.copy(is_checked = true)
                            } else user
                        }
                    }
                    is FailureGetUsers -> binding.tvEmpty.isVisible = true
                }
            }

            adapter = UsersAdapter(users ?: emptyList()) { user, isChecked ->
                if (isChecked) {
                    lifecycleScope.launch {
                        when (saveUser(user.copy(is_checked = true))) {
                            -1L -> showMessage(getString(R.string.user_is_not_saved))
                            else -> showMessage(getString(R.string.user_is_saved))
                        }
                    }
                }
            }

            binding.progressUsers.isVisible = false

            binding.rvUsers.layoutManager = LinearLayoutManager(context)
            binding.rvUsers.adapter = adapter
        }

        test()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(USERS_KEY, users as ArrayList<out Parcelable>)
        println("edz. onSaveInstanceState(). users - ${users as ArrayList<out Parcelable>}")

        super.onSaveInstanceState(outState)
    }

    private fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    private fun test() {
        val wiFi = (requireContext().getSystemService(Context.WIFI_SERVICE) as WifiManager).isWifiEnabled
        val wiFiState = (requireContext().getSystemService(Context.WIFI_SERVICE) as WifiManager).wifiState
        println("edz. wifi is enabled - $wiFi")
        println("edz. wiFiState - $wiFiState")
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        println("edz. capabilities - $capabilities")
        val internetCabality = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val isWiFiAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        val isWiFiAwareAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
        val isCellularAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        val isBluetoothAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
        val isVpnAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        val isEthernetAvailable = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        println("edz. internetCabality - $internetCabality")
        println("\nedz. isWiFiAvailable - $isWiFiAvailable")
        println("edz. isWiFiAwareAvailable - $isWiFiAwareAvailable")
        println("edz. isCellularAvailable - $isCellularAvailable")
        println("edz. isBluetoothAvailable - $isBluetoothAvailable")
        println("edz. isVpnAvailable - $isVpnAvailable")
        println("edz. isEthernetAvailable - $isEthernetAvailable")
    }
}
