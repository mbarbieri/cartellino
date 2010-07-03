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
package controllers;

import controllers.Secure.Security;
import java.util.List;
import models.*;
import play.Logger;
import play.data.validation.Required;
import play.mvc.*;

/**
 *
 * @author Matteo Barbieri <barbieri.matteo at gmail.com>
 */
@With(Secure.class)
public class Application extends Controller {

    public static void index() {     
        User user = User.find("byEmail",Security.connected()).first();
        List<Activity> activities = user.activities;
        render(user,activities);
    }

    public static void stop(@Required String activityId) {
        if(!validation.hasErrors()) {
            Activity a = Activity.findById(Long.parseLong(activityId));
            Logger.debug("Stop %s", a);
            a.stop();
        }
    }

    public static void start(@Required String activityId) {
        if(!validation.hasErrors()) {
            Activity a = Activity.findById(Long.parseLong(activityId));
            Logger.debug("Start %s", a);
            a.start();
        }
    }

}