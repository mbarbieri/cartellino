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

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Matteo Barbieri <barbieri.matteo at gmail.com>
 */
@Entity
public class Activity extends Model {

    @Required
    public String name;

    @ManyToOne
    public User user;

    public Activity(User user, String name) {
        this.user = user;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public TimeSlice getActiveTimeSlice() {
        return TimeSlice.find("byActivityAndEndTimeIsNull", this).first();
    }

    public void stop() {
        TimeSlice tm = getActiveTimeSlice();
        tm.endTime = new Date();
        tm.save();
    }

    public void start() {
        new TimeSlice(this,new Date()).save();
    }

}
