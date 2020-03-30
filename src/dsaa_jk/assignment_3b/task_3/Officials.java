package dsaa_jk.assignment_3b.task_3;

import java.util.ArrayList;
import java.util.List;

public class Officials {
    List<Official> officials = new ArrayList<>();

    public Officials(int number) {
        for(int i = 0; i < number; i++) {
            String name = Utilities.getLetter(i);
            this.officials.add(new Official(name));
        }
    }

    public Official getFreeOfficial() {
        for(Official official:officials) {
            if(official.isFree()) {
                return official;
            }
        }
        return null;
    }

    public void tick() {
        for(Official official:officials) {
            official.tick();
        }
    }

    int globalTime = 0;
}
