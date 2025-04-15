package com.saulms.verdantrealm.weapons;

import com.saulms.verdantrealm.data.GameResource;
import com.saulms.verdantrealm.weapons.Melee.*;

import java.util.List;

public class WeaponData {

    private static WeaponData weapons;

    public static void load() {
        weapons = GameResource.loadJson("equipment/weapons.json", WeaponData.class);
    }

    public static Weapon createMelee(Melee type, int tier) {
        return switch (type) {
            case SWORD -> weapons.getMelee().getSwords().get(tier - 1);
            case AXE -> weapons.getMelee().getAxes().get(tier - 1);
            case SPEAR -> weapons.getMelee().getSpears().get(tier - 1);
            case DAGGER -> weapons.getMelee().getDaggers().get(tier - 1);
            case MACE -> weapons.getMelee().getMaces().get(tier - 1);
        };
    }


    private MeleeData melee;

    public MeleeData getMelee() {
        return melee;
    }

    public void setMelee(MeleeData melee) {
        this.melee = melee;
    }

    public enum Melee {
        SWORD,
        AXE,
        SPEAR,
        DAGGER,
        MACE
    }

    public static class MeleeData {

        private List<Sword> swords;
        private List<Axe> axes;
        private List<Spear> spears;
        private List<Dagger> daggers;
        private List<Mace> maces;

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

        public List<Mace> getMaces() {
            return maces;
        }

        public void setMaces(List<Mace> maces) {
            this.maces = maces;
        }

    }

}
