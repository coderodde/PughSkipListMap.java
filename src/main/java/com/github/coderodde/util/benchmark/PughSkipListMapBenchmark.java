package com.github.coderodde.util.benchmark;

import com.github.coderodde.util.PughSkipListMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

public final class PughSkipListMapBenchmark {
    
    private static final int MAP_SIZE = 10_000;
    
    public static void main(String[] args) {
        
        Random random = new Random(13L);
        
        List<Integer> list = new ArrayList<>(MAP_SIZE);
        
        for (int i = 0; i < MAP_SIZE; i++) {
            list.add(i);
        }
        
        Collections.shuffle(list, random);
        
        PughSkipListMap<Integer, Long> skipListMap = 
                new PughSkipListMap<>(0.4, random);
        
        ConcurrentSkipListMap<Integer, Long> concurrentSkipListMap =
                new ConcurrentSkipListMap<>();
        
        long totalSkipListMap = 0L;
        long totalConcurrentSkipListMap = 0L;
        
        long start = System.currentTimeMillis();
        
        for (Integer key : list) {
            skipListMap.put(key, Long.valueOf(key));
        }
        
        long end = System.currentTimeMillis();
        
        totalSkipListMap += end - start;
        
        System.out.printf("PughSkipListMap.put in %d milliseconds.\n",
                          end - start);
        
        start = System.currentTimeMillis();
        
        for (Integer key : list) {
            concurrentSkipListMap.put(key, Long.valueOf(key));
        }
        
        end = System.currentTimeMillis();
        
        totalConcurrentSkipListMap += end - start;
        
        System.out.printf("ConcurrentSkipListMap.put in %d milliseconds.\n",
                          end - start);
        
        Collections.shuffle(list, random);
        
        start = System.currentTimeMillis();
        
        for (Integer key : list) {
            skipListMap.get(key);
        }
        
        end = System.currentTimeMillis();
        
        totalSkipListMap += end - start;
        
        System.out.printf("PughSkipListMap.get %d milliseconds.\n",
                          end - start);
        
        start = System.currentTimeMillis();
        
        for (Integer key : list) {
            concurrentSkipListMap.get(key);
        }
        
        end = System.currentTimeMillis();
        
        totalConcurrentSkipListMap += end - start;
        
        System.out.printf("ConcurrentSkipListMap.get %d milliseconds.\n",
                          end - start);
        
        Collections.shuffle(list, random);
        
        start = System.currentTimeMillis();
        
        for (Integer key : list) {
            skipListMap.remove(key);
        }
        
        end = System.currentTimeMillis();
        
        totalSkipListMap += end - start;
        
        System.out.printf("PughSkipListMap.remove %d milliseconds.\n",
                          end - start);
        
        start = System.currentTimeMillis();
        
        for (Integer key : list) {
            concurrentSkipListMap.remove(key);
        }
        
        end = System.currentTimeMillis();
        
        totalConcurrentSkipListMap += end - start;
        
        System.out.printf("ConcurrentSkipListMap.remove %d milliseconds.\n",
                          end - start);
        
        System.out.printf("PughSkipListMap total: %d milliseconds.\n",
                          totalSkipListMap);
        
        System.out.printf("ConcurrentSkipListMap total: %d milliseconds.\n",
                          totalConcurrentSkipListMap);
    }
}
