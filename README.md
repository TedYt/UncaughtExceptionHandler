# UncaughtExceptionHandler

1. 权限
    注意Android M 有些权限是要动态申请的，需要用户确认的。这个申请需要添加以下代码：
    if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this,
                new String []{Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }
    
    this 是指Activity。
    
    运行到这段代码后，会弹出一个对话框，有用户确认。
    
    需要动态申请的Runtime Permission 有：
    Dangerous Permissions:这些permission都称之为危险权限，因为跟用户隐私有关系
    
    group:android.permission-group.CONTACTS
      permission:android.permission.WRITE_CONTACTS
      permission:android.permission.GET_ACCOUNTS
      permission:android.permission.READ_CONTACTS

    group:android.permission-group.PHONE
      permission:android.permission.READ_CALL_LOG
      permission:android.permission.READ_PHONE_STATE
      permission:android.permission.CALL_PHONE
      permission:android.permission.WRITE_CALL_LOG
      permission:android.permission.USE_SIP
      permission:android.permission.PROCESS_OUTGOING_CALLS
      permission:com.android.voicemail.permission.ADD_VOICEMAIL

    group:android.permission-group.CALENDAR
      permission:android.permission.READ_CALENDAR
      permission:android.permission.WRITE_CALENDAR

    group:android.permission-group.CAMERA
      permission:android.permission.CAMERA

    group:android.permission-group.SENSORS
      permission:android.permission.BODY_SENSORS

    group:android.permission-group.LOCATION
      permission:android.permission.ACCESS_FINE_LOCATION
      permission:android.permission.ACCESS_COARSE_LOCATION

    group:android.permission-group.STORAGE
      permission:android.permission.READ_EXTERNAL_STORAGE
      permission:android.permission.WRITE_EXTERNAL_STORAGE

    group:android.permission-group.MICROPHONE
      permission:android.permission.RECORD_AUDIO

    group:android.permission-group.SMS
      permission:android.permission.READ_SMS
      permission:android.permission.RECEIVE_WAP_PUSH
      permission:android.permission.RECEIVE_MMS
      permission:android.permission.RECEIVE_SMS
      permission:android.permission.SEND_SMS
      permission:android.permission.READ_CELL_BROADCASTS
      
2. 判断SDK 版本
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        ...
     }
