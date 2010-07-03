/*
 *  Copyright (C) 2010 Matteo Barbieri <barbieri.matteo at gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Matteo Barbieri <barbieri.matteo at gmail.com>
 */
@Entity
public class User extends Model {

    @Required
    public String name;

    @Required
    @Email
    public String email;

    @Required
    @Password
    public String password;
    
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    public List<Activity> activities = new ArrayList();

    public User(String name,String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return name;
    }

}
