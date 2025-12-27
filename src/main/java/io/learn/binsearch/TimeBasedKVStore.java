package io.learn.binsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKVStore {
    Map<String, List<TimeValue>> map;

    public TimeBasedKVStore() {
        this.map = new HashMap<>();
    }

    public void set(String key, int timestamp, String value) {
        map.putIfAbsent(key, new ArrayList<>());
        List<TimeValue> list = map.get(key);
        if (list.isEmpty()) {
            list.add(new TimeValue(timestamp, value));
            return;
        }
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + (high - low) / 2);
            TimeValue midValue = list.get(mid);
            if (midValue.timestamp == timestamp) {
                midValue.value = value;
            } else if (midValue.timestamp > timestamp) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        list.add(low, new TimeValue(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        List<TimeValue> list = map.get(key);
        if (timestamp < list.get(0).timestamp) {
            return "";
        }
        if (timestamp > list.get(list.size() - 1).timestamp) {
            return list.get(list.size() - 1).value;
        }
        int low = 0;
        int high = list.size();
        while (low <= high) {
            int mid = (low + (high - low) / 2);
            TimeValue midTimeValue = list.get(mid);
            if (midTimeValue.timestamp == timestamp) {
                return midTimeValue.value;
            } else if (midTimeValue.timestamp > timestamp) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return list.get(low - 1).value;
    }

    public static void main(String[] args) {
        TimeBasedKVStore kvStore = new TimeBasedKVStore();
        kvStore.set("foo", 1, "bar");
        assert kvStore.get("foo", 1).equals("bar") : "Got unexpected value";
        assert kvStore.get("foo", 3).equals("bar") : "Got unexpected value";
        kvStore.set("foo", 4, "bar2");
        assert kvStore.get("foo", 4).equals("bar2") : "Got unexpected value";
        assert kvStore.get("foo", 5).equals("bar2") : "Got unexpected value";
    }
}

class TimeValue {
    int timestamp;
    String value;

    public TimeValue(int timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }
}
