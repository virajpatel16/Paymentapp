package com.example.paymentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paymentapp.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(),PaymentResultListener {
    var binding=ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.idBtnPay.setOnClickListener {
            // on below line getting amount from edit text
          val amt=  binding.idEdtAmount.text.toString()


            // rounding off the amount.
            val amount = Math.round(amt.toFloat() * 100).toInt()

            // on below line we are
            // initializing razorpay account
            val checkout = Checkout()

            // on the below line we have to see our id.
            checkout.setKeyID("rzp_test_QxYr17w7NOFYkx")

            // set image
            checkout.setImage(R.drawable.download)

            // initialize json object
            val obj = JSONObject()
            try {
                // to put name
                obj.put("name", "Geeks for Geeks")

                // put description
                obj.put("description", "Test payment")

                // to set theme color
                obj.put("theme.color", "")

                // put the currency
                obj.put("currency", "INR")

                // put amount
                obj.put("amount", amount)

                // put mobile number
                obj.put("prefill.contact", "49358503")

                // put email
                obj.put("prefill.email", "virajgondaliya@gmail.com")

                // open razorpay to checkout activity
                checkout.open(this@MainActivity, obj)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this,"your payment is succesfully"+p0,Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {

        Toast.makeText(this,"your payment is fail"+p0,Toast.LENGTH_SHORT).show()
    }
}