package fr.azuxul.showarmsstand;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.Rotations;

import java.util.Random;

/**
 * ShowArmorStand
 *
 * @author Azuxul
 * @version 1.0
 */
public class ShowArmorStand {

    private ShowArmorStand() {

    }

    public static void applyRandomRotations(EntityArmorStand armorStand, Random rand) {

        Rotations rotations = armorStand.getHeadRotation();
        float f = rand.nextFloat() * 5.0F;
        float f1 = rand.nextFloat() * 20.0F - 10.0F;
        Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
        armorStand.setHeadRotation(rotations1);
        rotations = armorStand.getBodyRotation();
        f = rand.nextFloat() * 10.0F - 5.0F;
        rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
        armorStand.setBodyRotation(rotations1);
    }
}
