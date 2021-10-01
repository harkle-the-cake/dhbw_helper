package eu.boxwork.dhbw.examples.base;

import java.io.Serializable;

public class Node implements Serializable {
    private String ip = "";
    private int port = -1;
    private int id = -1;
    private Role role = Role.UNKNOWN ;
    /**
     * Default - Constructor
     */
    public Node ()
    {}
    /* GETTER - SETTER */

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
