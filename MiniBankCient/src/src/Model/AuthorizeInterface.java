/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;

/**
 *
 * @author Damian Stępniak
 */
public interface AuthorizeInterface {
    public void setPassword(String password);
    public void setUsername(String username);
    public String getToken();
    public void clear();
}
