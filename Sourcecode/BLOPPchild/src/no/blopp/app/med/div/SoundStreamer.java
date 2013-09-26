package no.blopp.app.med.div;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

public class SoundStreamer
{
	private String url;
	private OnCompletionListener completionListener;
	private MediaPlayer mediaPlayer;
	
	public SoundStreamer(String url, OnCompletionListener completionListener)
	{
		this.url = url;
		this.completionListener = completionListener;
	}
	
	public SoundStreamer(String url)
	{
		this.url = url;
	}
	
	public MediaPlayer getMediaPlayer()
	{
		return mediaPlayer;
	}
	
	public void play()
	{
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try
		{
			mediaPlayer.setDataSource(url);
		} catch (IllegalArgumentException e)
		{
			Log.e("setAudioStreamType", e.getMessage());
		} catch (SecurityException e)
		{
			Log.e("setAudioStreamType", e.getMessage());
		} catch (IllegalStateException e)
		{
			Log.e("setAudioStreamType", e.getMessage());
		} catch (IOException e)
		{
			Log.e("setAudioStreamType", e.getMessage());
		}
		mediaPlayer.prepareAsync();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mp)
			{
				mp.start();
			}
		});
		mediaPlayer.setOnCompletionListener(new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				mp.release();
			}
		});
		if (completionListener != null)
		{
			mediaPlayer.setOnCompletionListener(completionListener);
		}
	}
}
