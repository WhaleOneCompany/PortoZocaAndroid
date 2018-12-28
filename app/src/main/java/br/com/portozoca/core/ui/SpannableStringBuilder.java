package br.com.portozoca.core.ui;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

public class SpannableStringBuilder {

    private final SpannableString spannableString;

    private SpannableStringBuilder(String s) {
        spannableString = new SpannableString(s);
    }

    public SpannableStringBuilder size(float size){
        return size(size, 0, spannableString.length());
    }

    public SpannableStringBuilder size(float size, int s0, int end){
        return size(size, s0, end,0);
    }

    public SpannableStringBuilder size(float proportion, int s0, int end, int flags){
        setSpan(new RelativeSizeSpan(proportion), s0, end, flags);
        return this;
    }

    public SpannableStringBuilder color(int color){
        return color(color, 0, spannableString.length());
    }

    public SpannableStringBuilder color(int color, int s0, int end){
        return color(color, s0, end, 0);
    }

    public SpannableStringBuilder color(int color, int s0, int end, int flags){
        setSpan(new ForegroundColorSpan(color), s0, end, flags);
        return this;
    }

    public SpannableStringBuilder bold(){
        return bold(0,spannableString.length());
    }

    public SpannableStringBuilder bold(int s0, int end){
        return bold(s0, end, 0);
    }

    public SpannableStringBuilder bold(int s0, int end, int flags){
        setSpan(new StyleSpan(Typeface.BOLD), s0, end, flags);
        return this;
    }

    public SpannableStringBuilder underscored(){
        return underscored(0, spannableString.length());
    }

    public SpannableStringBuilder underscored(int s0, int end){
        return underscored(s0, end, 0);
    }

    public SpannableStringBuilder underscored(int s0, int end, int flags){
        setSpan(new UnderlineSpan(), s0, end, flags);
        return this;
    }

    public SpannableStringBuilder link(String url){
        return link(url, 0, spannableString.length());
    }

    public SpannableStringBuilder link(String url, int s0, int end){
        return link(url, s0, end, 0);
    }

    public SpannableStringBuilder link(String url, int s0, int end, int flags){
        setSpan(new URLSpan(url), s0, end, flags);
        return this;
    }

    // ------------------------------------------------------ //
    // COMBOS
    // ------------------------------------------------------ //

    public SpannableStringBuilder bold_underscored(int s0, int end){
        bold(s0, end);
        return underscored(s0, end);
    }

    private void setSpan(Object what, int start, int end, int flags){
        spannableString.setSpan(what, start, end, flags);
    }

    public SpannableString build(){
        return spannableString;
    }

    public static SpannableStringBuilder create(String s){
        return new SpannableStringBuilder(s);
    }

}
