package com.bramex.enfermericclient.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.bramex.enfermericclient.databinding.ActivityRegisterBinding
import com.bramex.enfermericclient.models.Client
import com.bramex.enfermericclient.providers.AuthProvider
import com.bramex.enfermericclient.providers.ClientProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authProvider = AuthProvider()
    private val clientProvider = ClientProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.BtnGoToLogin.setOnClickListener { goToLogin() }
        binding.btnRegister.setOnClickListener { register() }
    }

    private fun register(){
        val name = binding.textFieldName.text.toString()
        val lastName = binding.textFieldLastName.text.toString()
        val phone = binding.textFieldPhone.text.toString()
        val email = binding.textFieldEmail.text.toString()
        val password = binding.textFieldPassword.text.toString()
        val confirmPassword = binding.textFieldConfirmPassword.text.toString()
        
        if (isValidForm(name, lastName, phone, email, password, confirmPassword)){
            authProvider.register(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    val client = Client(
                        id = authProvider.getId(),
                        name = name,
                        lastname = lastName,
                        phone = phone,
                        email = email
                    )
                    clientProvider.create(client).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this@RegisterActivity, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@RegisterActivity, "Error en el almacenamiento de los datos de usuario!!!${it.exception.toString()}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registro fallido ${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidForm(
        name: String,
        lastName:String,
        phone: String,
        email:String,
        password: String,
        confirmPassword: String ): Boolean{

        if(name.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su Nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if(lastName.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su Apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if(phone.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su número de Teléfono", Toast.LENGTH_SHORT).show()
            return false
        }

        if(email.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su Correo Electrónico", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.isEmpty()){
            Toast.makeText(this, "Por favor ingrese su Contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if(confirmPassword.isEmpty()){
            Toast.makeText(this, "Por favor confirme su Contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if(confirmPassword != password){
            Toast.makeText(this, "Las contraseñas deben de ser iguales", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (password.length <= 6){
            Toast.makeText(this, "La contraseña debe de tener al menos seis caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun goToLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}