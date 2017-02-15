package com.kochnev;

import lib.Cache;
import lib.ILink;
import lib.IResult;

public class Main {

    public static void main(String[] args) {
        Cache<KeyVal, ResVal> cache = new Cache<KeyVal, ResVal>(10, val ->
                new ResVal(val.toString() + System.currentTimeMillis()));
        for (int i = 0; i < 20; i++) {
            KeyVal key = new KeyVal("omega" + i + " ");
            System.out.println(cache.load(key));
        }
        System.out.println("----------");
        cache.printCache();
    }

    static class KeyVal implements ILink {
        private String link;
        KeyVal(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return link;
        }
    }

    static class ResVal implements IResult {
        private String link;
        ResVal(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return link;
        }
    }
}
