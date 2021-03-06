/****************************************************************************
*                                                                           *
* Project64 - A Nintendo 64 emulator.                                       *
* http://www.pj64-emu.com/                                                  *
* Copyright (C) 2012 Project64. All rights reserved.                        *
*                                                                           *
* License:                                                                  *
* GNU/GPLv2 http://www.gnu.org/licenses/gpl-2.0.html                        *
*                                                                           *
****************************************************************************/
package emu.project64.settings;

import emu.project64.AndroidDevice;
import emu.project64.R;
import emu.project64.jni.NativeExports;
import emu.project64.jni.SettingsID;
import emu.project64.jni.SystemEvent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (NativeExports.SettingsLoadBool(SettingsID.GameRunning_CPU_Running.getValue()) == true)
        {
            NativeExports.ExternalEvent( SystemEvent.SysEvent_ResumeCPU_FromMenu.getValue());
        }
        setContentView(R.layout.settings_activity);
        
        // Add the tool bar to the activity (which supports the fancy menu/arrow animation)
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        toolbar.setTitle( getString(R.string.settings_title) );
        setSupportActionBar( toolbar );
        ActionBar actionbar = getSupportActionBar();
        
        if (AndroidDevice.IS_ICE_CREAM_SANDWICH)
        {
            actionbar.setHomeButtonEnabled(true);
            actionbar.setDisplayHomeAsUpEnabled(true);
    	}
        
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefs.edit().clear()
        .putBoolean("audio_Enabled",NativeExports.SettingsLoadBool(SettingsID.Plugin_EnableAudio.getValue()))
        .putBoolean("UserInterface_BasicMode",NativeExports.SettingsLoadBool(SettingsID.UserInterface_BasicMode.getValue()))
        .putBoolean("Debugger_Enabled",NativeExports.SettingsLoadBool(SettingsID.Debugger_Enabled.getValue()))
        .putBoolean("Debugger_GenerateLogFiles",NativeExports.SettingsLoadBool(SettingsID.Debugger_GenerateLogFiles.getValue()))
        .putBoolean("Debugger_DisplaySpeed",NativeExports.SettingsLoadBool(SettingsID.UserInterface_DisplayFrameRate.getValue()))
        .putBoolean("Debugger_CpuUsage",NativeExports.SettingsLoadBool(SettingsID.UserInterface_ShowCPUPer.getValue()))
        .putString("Debugger_DisplaySpeedType",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.UserInterface_FrameDisplayType.getValue())))
        .putString("Debugger_TraceMD5",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceMD5.getValue())))
        .putString("Debugger_TraceThread",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceThread.getValue())))
        .putString("Debugger_TracePath",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TracePath.getValue())))
        .putString("Debugger_TraceSettings",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceSettings.getValue())))
        .putString("Debugger_TraceUnknown",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceUnknown.getValue())))
        .putString("Debugger_TraceAppInit",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceAppInit.getValue())))
        .putString("Debugger_TraceAppCleanup",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceAppCleanup.getValue())))
        .putString("Debugger_TraceN64System",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceN64System.getValue())))
        .putString("Debugger_TracePlugins",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TracePlugins.getValue())))
        .putString("Debugger_TraceGFXPlugin",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceGFXPlugin.getValue())))
        .putString("Debugger_TraceAudioPlugin",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceAudioPlugin.getValue())))
        .putString("Debugger_TraceControllerPlugin",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceControllerPlugin.getValue())))
        .putString("Debugger_TraceRSPPlugin",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceRSPPlugin.getValue())))
        .putString("Debugger_TraceRSP",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceRSP.getValue())))
        .putString("Debugger_TraceAudio",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceAudio.getValue())))
        .putString("Debugger_TraceRegisterCache",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceRegisterCache.getValue())))
        .putString("Debugger_TraceRecompiler",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceRecompiler.getValue())))
        .putString("Debugger_TraceTLB",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceTLB.getValue())))
        .putString("Debugger_TraceProtectedMEM",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceProtectedMEM.getValue())))
        .putString("Debugger_TraceUserInterface",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceUserInterface.getValue())))
        .putString("Debugger_TraceRomList",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceRomList.getValue())))
        .putString("Debugger_TraceExceptionHandler",String.valueOf(NativeExports.SettingsLoadDword(SettingsID.Debugger_TraceExceptionHandler.getValue())))
        .apply();  

        sharedPrefs.registerOnSharedPreferenceChangeListener(this);
        
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new SettingsFragment()).commit();
        }
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch (item.getItemId()) 
        {
        case android.R.id.home:
            if (!getSupportFragmentManager().popBackStackImmediate())
                finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
    	if (key.equals("UserInterface_BasicMode"))
    	{
    		NativeExports.SettingsSaveBool(SettingsID.UserInterface_BasicMode.getValue(), sharedPreferences.getBoolean(key,false));
    		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new SettingsFragment()).commit();
    	}
       	else if (key.equals("audio_Enabled")) { NativeExports.SettingsSaveBool(SettingsID.Plugin_EnableAudio.getValue(), sharedPreferences.getBoolean(key,false)); }
       	else if (key.equals("Debugger_Enabled")) { NativeExports.SettingsSaveBool(SettingsID.Debugger_Enabled.getValue(), sharedPreferences.getBoolean(key,false)); }
    	else if (key.equals("Debugger_GenerateLogFiles")) { NativeExports.SettingsSaveBool(SettingsID.Debugger_GenerateLogFiles.getValue(), sharedPreferences.getBoolean(key,false)); }
    	else if (key.equals("Debugger_CpuUsage")) { NativeExports.SettingsSaveBool(SettingsID.UserInterface_ShowCPUPer.getValue(), sharedPreferences.getBoolean(key,false)); }
    	else if (key.equals("Debugger_DisplaySpeed")) { NativeExports.SettingsSaveBool(SettingsID.UserInterface_DisplayFrameRate.getValue(), sharedPreferences.getBoolean(key,false)); }
    	else if (key.equals("Debugger_DisplaySpeedType")) { NativeExports.SettingsSaveDword(SettingsID.UserInterface_FrameDisplayType.getValue(), Integer.valueOf(sharedPreferences.getString(key, "0"))); }
    	else if (key.equals("Debugger_TraceMD5")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceMD5.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceThread")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceThread.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TracePath")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TracePath.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceSettings")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceSettings.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceUnknown")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceUnknown.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceAppInit")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceAppInit.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceAppCleanup")) { NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceAppCleanup.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1"))); }
    	else if (key.equals("Debugger_TraceN64System"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceN64System.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TracePlugins"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TracePlugins.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceGFXPlugin"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceGFXPlugin.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceAudioPlugin"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceAudioPlugin.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceControllerPlugin"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceControllerPlugin.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceRSPPlugin"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceRSPPlugin.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceRSP"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceRSP.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceAudio"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceAudio.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceRegisterCache"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceRegisterCache.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceRecompiler"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceRecompiler.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceTLB"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceTLB.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceProtectedMEM"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceProtectedMEM.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceUserInterface"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceUserInterface.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceRomList"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceRomList.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    	else if (key.equals("Debugger_TraceExceptionHandler"))
    	{
    		NativeExports.SettingsSaveDword(SettingsID.Debugger_TraceExceptionHandler.getValue(), Integer.valueOf(sharedPreferences.getString(key, "1")));
    	}
    }
}
