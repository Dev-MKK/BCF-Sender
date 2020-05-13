## **Blogger Contact Form Auto Sender**
[![](https://jitpack.io/v/KhunHtetzNaing/BCF-Sender.svg)](https://jitpack.io/#KhunHtetzNaing/BCF-Sender)

![နမူနာ Appication](https://github.com/KhunHtetzNaing/BCF-Sender/raw/master/photos/send.gif)
Android မှာအသုံးပြုသူတွေရဲ့ Feedback သို့မဟုတ် ဆက်သွယ်ပေးပို့လာမယ့်စာတွေကို
အပိုအခကြေးငွေမကုန်အောင် Blogspot မှာအခမဲ့ပေးထားတဲ့
Contact Form ကိုအသုံးပြုနိုင်ပါတယ်။
အဲ့ဒီ Form ကနေပေးပို့သမျှကို မိမိတို့ Blogger နဲ့ချိတ်ဆက်ထားသည့်
Gmail သို့ Blogger Contact Form အမည်ဖြင့်ရောက်ရှိလာမည်။
အဲ့ဒီအတွက် Android ဘက်မှာသုံးလိုပါက WebView ကိုအသုံးပြုလို့ရပေမယ့်
ဒီ Library လေးကတော့ WebView နဲ့ Javascript ကိုအသုံးချပြီး
Android ဘက်ကနေ Contact Form ကိုအလိုအလျောက်ဖြည့်ပြီး
ပေးပို့ပေးသွားမှာဖြစ်ပါတယ်။

![Gmail သို့ဝင်ရောက်လာပုံ](https://github.com/KhunHtetzNaing/BCF-Sender/raw/master/photos/received.gif)

## လိုအပ်ချက်
 - Blogger အသုံးပြုထားသည့် Blog/Website တစ်ခု
 - ထို Blog အတွင်း Contact Form ကို Layout မှတဆင့်ထည့်သွင်းထားရမည်

## အသုံးပြုနည်း
ပထမဦးစွာ  **build.gradle(project)**  အတွင်းမှာ

```
allprojects {
  repositories {  
		//ဤနေရာတွင်အခြားကုဒ်များရှိနေမည်
        maven { url "https://jitpack.io" }  //ဒီကုဒ်လေးထည့်သွင်းပေးရန်လို
 }}
```
ပြီးရင်  **build.gradle(app)**  ထဲမှာ

```
dependencies {  
	//ဤနေရာတွင်အခြားကုဒ်များရှိနေမည်
	implementation 'com.github.KhunHtetzNaing:BCF-Sender:1.0'
	//ဒီကုဒ်လေးထည့်သွင်းပေးရန်လို
}
```
**Android Studio** မှာတွေဆိုရင်တော့ **Sync Now** လုပ်ပေးပါ။  
**AIDE** မှာဆိုရင်တော့ **Save** ပြီး **Download** လုပ်ခိုင်းရင်လုပ်ပေးပါ။  
**မှတ်ချက်။ ။အင်တာနက်ဖွင့်ထားဖို့လိုပါမယ်**

မိမိအသုံးပြုလိုသည် Activity မှတဆင့်

    new BCFSender(getApplicationContext(), "https://yourTarget.blogspot.com", new BCFSender.OnSendListener() {  
        @Override  
      public void onResult(boolean success, String message) {  
            if (success){  
                //ပေးပို့ခြင်းအောင်မြင်ပါသည်  
		    }else {  
                //ပေးပို့ခြင်းမအောင်မြင်ပါ။  
		    }  
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();  
      }  
    }).send("ပေးပို့သူအမည်","ပေးပို့သူ Email","အကြောင်းအရာ");

## သတိပြုချက်
Contact Form ဖြစ်သည့်အတွက်အသုံးပြုသူများဆက်သွယ်လိုသည့်
နေရာတွင်သာအသုံးပြုရန်သင့်တော်ပါသည်။
မကြာခဏထပ်ခါတလဲလဲပေးပို့၍မရအောင် Google ဘက်မှ
ကန့်သတ်ထားခြင်းကြောင့်ဖြစ်သည်။
ထိုကြောင့်အနည်းဆုံး ၅ မိနစ်ခြားတစ်ကြိမ်သာပေးပို့ရန်သင့်တော်သည်။

*အကယ်၍သင့် Blogspot သည် HTTPS အသုံးပြုထားခြင်းမရှိပါက  
**AndroidManifest.xml**  ထဲမှာ*

```
 <application .....
     android:usesCleartextTraffic="true">
```

## ယခု Library အသုံးပြုထားသော App များ
<a  href='https://play.google.com/store/apps/details?id=com.mgngoe.zfont'><img  alt='Get it on Google Play'  src='https://github.com/KhunHtetzNaing/BCF-Sender/raw/master/photos/zfont.png' height=100px/></a>

<a  href='https://play.google.com/store/apps/details?id=com.pky.mifontinstaller'><img  alt='Get it on Google Play'  src='https://github.com/KhunHtetzNaing/BCF-Sender/raw/master/photos/miui.png' height=90px/></a>
