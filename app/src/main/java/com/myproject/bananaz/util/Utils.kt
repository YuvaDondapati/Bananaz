package yuvadon.demos.countries.util

import android.content.Context
import android.util.Patterns
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.bananaz.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(url: Int?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun isEmailValid(emailAddress: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}

fun isPasswordsMatch(pw: String, cnfmPw: String): Boolean {
    return pw == cnfmPw
}

fun isMobileNumberValid(mobileNumber: String): Boolean {
    return mobileNumber.length == 10
}
fun isInvalidMobile(mobileNumber: String): Boolean {
    return mobileNumber.length < 10
}