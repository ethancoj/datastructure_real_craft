    package net.ethan.tempestmod.entity.custom;

    import net.minecraft.core.BlockPos;
    import net.minecraft.util.RandomSource;
    import net.minecraft.world.entity.EntityType;
    import net.minecraft.world.entity.Mob;
    import net.minecraft.world.entity.MobSpawnType;
    import net.minecraft.world.entity.PathfinderMob;
    import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
    import net.minecraft.world.entity.ai.attributes.Attributes;
    import net.minecraft.world.entity.ai.goal.*;
    import net.minecraft.world.entity.animal.Animal;
    import net.minecraft.world.entity.player.Player;
    import net.minecraft.world.level.Level;
    import net.minecraft.world.level.LevelAccessor;
    import net.minecraft.world.level.ServerLevelAccessor;
    import net.minecraft.world.level.block.Blocks;

    import java.util.Random;

    public class DwarfEntity extends PathfinderMob {
        public DwarfEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
            super(entityType, level);
        }

        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(0, new FloatGoal(this));
            this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.25));
            this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 5.0F));
            this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        }

        public static AttributeSupplier.Builder createAttributes() {
            return PathfinderMob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 10.0)
                    .add(Attributes.MOVEMENT_SPEED, 0.5)
                    .add(Attributes.ARMOR_TOUGHNESS, 1)
                    .add(Attributes.ATTACK_DAMAGE, 1.0)
                    .add(Attributes.FOLLOW_RANGE, 35.0);
        }

        public static boolean canSpawn(EntityType<DwarfEntity> type, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
            return Mob.checkMobSpawnRules(type,level,spawnReason,pos,random);
        }
    }
