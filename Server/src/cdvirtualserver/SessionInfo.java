/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;

import java.util.Vector;

/**
 *
 * @author Chayan-Dhaddha
 */
public class SessionInfo {
    public String session_id,mentor_id,topic,desc,date;
    public boolean current_status;
    public Vector<EnrolledInSession>enrolled;
    int count=0;
    public Vector<String> form_query;
}
