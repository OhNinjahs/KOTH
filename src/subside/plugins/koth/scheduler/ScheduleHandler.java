package subside.plugins.koth.scheduler;

import java.util.ArrayList;
import java.util.List;

import subside.plugins.koth.adapter.Koth;
import subside.plugins.koth.loaders.ScheduleLoader;

public class ScheduleHandler {
    private static ScheduleHandler instance = new ScheduleHandler();
    private List<Schedule> schedules = new ArrayList<>();

    public static ScheduleHandler getInstance() {
        return instance;
    }

    public Schedule getNextEvent() {
        Schedule ret = null;
        for (Schedule sched : schedules) {
            if (ret == null) {
                ret = sched;
            } else if (sched.getNextEvent() < ret.getNextEvent()) {
                ret = sched;
            }
        }
        return ret;
    }

    public Schedule getNextEvent(Koth koth) {
        Schedule ret = null;
        for (Schedule sched : schedules) {
            if (sched.getKoth().equalsIgnoreCase(koth.getName())) {
                if (ret == null) {
                    ret = sched;
                } else if (sched.getNextEvent() < ret.getNextEvent()) {
                    ret = sched;
                }
            }
        }
        return ret;
    }

    public String removeId(int id) {
        if (schedules.get(id) == null) {
            return null;
        }
        String koth = schedules.get(id).getKoth();

        schedules.remove(id);
        ScheduleLoader.save();
        return koth;
    }

    public void tick() {
        for (Schedule schedule : schedules) {
            schedule.tick();
        }
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
