package com.bramex.enfermericclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.bramex.enfermericclient.databinding.ActivityMainBinding
import com.bramex.enfermericclient.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

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
            Toast.makeText(this, "El formulario es válido", Toast.LENGTH_SHORT).show()
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