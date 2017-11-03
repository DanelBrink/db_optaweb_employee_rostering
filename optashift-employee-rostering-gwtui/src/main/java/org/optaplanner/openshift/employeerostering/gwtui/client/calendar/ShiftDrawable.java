package org.optaplanner.openshift.employeerostering.gwtui.client.calendar;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import elemental2.dom.CanvasRenderingContext2D;
import org.optaplanner.openshift.employeerostering.gwtui.client.canvas.CanvasUtils;
import org.optaplanner.openshift.employeerostering.gwtui.client.canvas.ColorUtils;
import org.optaplanner.openshift.employeerostering.gwtui.client.common.CommonUtils;

public class ShiftDrawable extends AbstractDrawable implements TimeRowDrawable {
    String spot;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String color;
    int index;
    TwoDayView view;
    
    
    public ShiftDrawable(TwoDayView view, String spot, LocalDateTime startTime, LocalDateTime endTime, String color, int index) {
        this.view = view;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spot = spot;
        this.color = color;
        this.index = index;
    }

    @Override
    public void doDraw(CanvasRenderingContext2D g) {
        CanvasUtils.setFillColor(g, color);
        
        double start = startTime.toEpochSecond(ZoneOffset.UTC) / 60;
        double end = endTime.toEpochSecond(ZoneOffset.UTC) / 60;
        double duration = end - start;
        
        CanvasUtils.drawCurvedRect(g, getLocalX(), getLocalY(), duration*view.getWidthPerMinute(), view.getSpotHeight());
        
        CanvasUtils.setFillColor(g, ColorUtils.getTextColor(color));
        g.fillText(spot, getLocalX(), getLocalY() + view.getSpotHeight());
        
        if (view.getMouseX() >= getGlobalX() && view.getMouseX() <= getGlobalX() + view.getWidthPerMinute()*duration &&
                view.getMouseY() >= getGlobalY() && view.getMouseY() <= getGlobalY() + view.getSpotHeight() ) {
            view.preparePopup(this.toString());
            
        }
    }
    
    @Override
    public void doDrawAt(CanvasRenderingContext2D g, double x, double y) {
        CanvasUtils.setFillColor(g, color);
        
        double start = startTime.toEpochSecond(ZoneOffset.UTC) / 60;
        double end = endTime.toEpochSecond(ZoneOffset.UTC) / 60;
        double duration = end - start;
        
        CanvasUtils.drawCurvedRect(g, x, y, duration*view.getWidthPerMinute(), view.getSpotHeight());
        
        CanvasUtils.setFillColor(g, ColorUtils.getTextColor(color));
        g.fillText(spot, x, y + view.getSpotHeight());
        
        if (view.getMouseX() >= x && view.getMouseX() <= y + view.getWidthPerMinute()*duration &&
                view.getMouseY() >= x && view.getMouseY() <= y + view.getSpotHeight() ) {
            view.preparePopup(this.toString());
            
        }
    }

    @Override
    public double getLocalX() {
        double start= startTime.toEpochSecond(ZoneOffset.UTC) / 60;
        return start*view.getWidthPerMinute();
    }

    @Override
    public double getLocalY() {
        Integer cursorIndex = view.getSpotCursorIndex(spot);
        return (null != cursorIndex && cursorIndex > index)? index*view.getSpotHeight() : (index+1)*view.getSpotHeight();
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(spot);
        out.append(' ');
        out.append(CommonUtils.pad(startTime.getHour() + "", 2));
        out.append(':');
        out.append(CommonUtils.pad(startTime.getMinute() + "", 2));
        out.append('-');
        out.append(CommonUtils.pad(endTime.getHour() + "", 2));
        out.append(':');
        out.append(CommonUtils.pad(endTime.getMinute() + "", 2));
        return out.toString();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String getGroupId() {
        return spot;
    }

}