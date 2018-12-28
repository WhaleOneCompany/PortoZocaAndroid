package br.com.portozoca.core;

import android.widget.SeekBar;

public abstract class ColorSeekBarEventListener implements  SeekBar.OnSeekBarChangeListener {

    public abstract void run(Integer value);

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        run(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
