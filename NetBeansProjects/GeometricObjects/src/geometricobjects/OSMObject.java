/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometricobjects;

import geometricobjects.GeometricObject;
import geometricobjects.Rectangle;
import geometricobjects.Point;
/**
 *
 * @author Kowalewski
 */
public class OSMObject extends GeometricObject {
    private long _osm_id;
    protected float _longitude;
    protected float _latitude;
    private String _timestamp;
    private String  _uid;
    private String _user;
    private boolean _visible;
    private String _version;
    private String _changeset;
    
    public OSMObject(Point Basepoint) {
        super(Basepoint);
    }
    
    public long getOSMID() {
        return this._osm_id;
    }
    
    public void setID(String ID) {
        this._osm_id = Long.parseLong(ID);
    }
    
    public float getLongitude() {
        return this._longitude;
    }
    
    public void setLongitude(String Longitude) {
        this._longitude = Float.parseFloat(Longitude);
        this.setXCoord(1);
    }
    
    public float getLatitude() {
        return this._latitude;
    }
    
    public void setLatitude(String Latitude) {
        this._latitude = Float.parseFloat(Latitude);
        this.setYCoord(0);
    }

    public void setTimestamp(String Timestamp) {
        this._timestamp = Timestamp;
    }

    public void setUid(String Uid) {
        this._uid = Uid;
    }

    public void setUser(String User) {
        this._user = User;
    }

    public void setVisible(boolean Visible) {
        this._visible = Visible;
    }

    public void setVersion(String Version) {
        this._version = Version;
    }

    public void setChangeset(String Changeset) {
        this._changeset = Changeset;
    }
}
