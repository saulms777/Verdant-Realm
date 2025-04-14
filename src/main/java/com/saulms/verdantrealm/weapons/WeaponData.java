package com.saulms.verdantrealm.weapons;

import com.saulms.verdantrealm.weapons.Melee.*;

import java.util.List;

public class WeaponData {

    private MeleeStats melee;

    public MeleeStats getMelee() {
        return melee;
    }

    public void setMelee(MeleeStats melee) {
        this.melee = melee;
    }

    public static class MeleeStats {
        private List<Sword> swords;
        private List<Axe> axes;
        private List<Spear> spears;
        private List<Dagger> daggers;
        private List<Hammer> hammers;

        public List<Sword> getSwords() {
            return swords;
        }

        public void setSwords(List<Sword> swords) {
            this.swords = swords;
        }

        public List<Axe> getAxes() {
            return axes;
        }

        public void setAxes(List<Axe> axes) {
            this.axes = axes;
        }

        public List<Spear> getSpears() {
            return spears;
        }

        public void setSpears(List<Spear> spears) {
            this.spears = spears;
        }

        public List<Dagger> getDaggers() {
            return daggers;
        }

        public void setDaggers(List<Dagger> daggers) {
            this.daggers = daggers;
        }

        public List<Hammer> getHammers() {
            return hammers;
        }

        public void setHammers(List<Hammer> hammers) {
            this.hammers = hammers;
        }
    }

}
