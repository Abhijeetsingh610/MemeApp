package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.Intents.Insert.ACTION
import android.provider.ContactsContract.Intents.Insert.ACTION
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var currentimageurl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()

    }
    private lateinit var binding:ActivityMainBinding
    private fun loadmeme()
    {
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                currentimageurl = response.getString("url")
                Glide.with(this).load(currentimageurl).into(binding.memeImageview)
            },
            Response.ErrorListener {
               Toast.makeText(this , "Something went wrong" , Toast.LENGTH_LONG ).show()
            })

        queue.add(JsonObjectRequest)
    }


    fun sharememe(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT , "Hey  , checkout this cool meme $currentimageurl")
        val chooser =   Intent.createChooser(intent, "Share this meme using.....")
        startActivity(chooser)
    }

    fun nextmeme(view: View) {
        loadmeme()


    }
}

