<script setup lang="ts">
  import type { Stat } from '~/types/Character';
  const { stat, characterClassName } = defineProps<{
    stat: Stat | null;
    characterClassName: string;
  }>();
  const { $meta } = useNuxtApp();
  const isPhysical = computed(() =>
    $meta.character.physicalClass.includes(characterClassName == null ? '' : characterClassName),
  );

  const defaultCharacterStat = {
    physicalAttack: 0,
    magicAttack: 0,
    defense: 0,
    strength: 0,
    dexterity: 0,
    intelligence: 0,
    will: 0,
    luck: 0,
    maxHealth: 0,
    maxStamina: 0,
    attackSpeed: 0,
    additionalDamage: 0,
    critical: 0,
    criticalDamage: 0,
    criticalResistance: 0,
    balance: 0,
    attackLimitRelease: 0,
    resistance: 0,
  };

  const mergedCharacterStat = computed(() => ({
    ...defaultCharacterStat,
    ...(stat ?? {}),
  }));

  const {
    physicalAttack,
    magicAttack,
    defense,
    strength,
    dexterity,
    intelligence,
    will,
    luck,
    maxHealth,
    maxStamina,
    attackSpeed,
    additionalDamage,
    critical,
    criticalDamage,
    criticalResistance,
    balance,
    attackLimitRelease,
    resistance,
  } = mergedCharacterStat.value;
</script>

<template>
  <BaseCard>
    <div class="mx-auto max-w-7xl">
      <div class="flex flex-col justify-center">
        <h4 class="text-center text-xl font-bold">능력치</h4>
        <div class="grid min-h-96 grid-cols-2 gap-6 p-6">
          <CharacterStatItem
            stat-name="힘"
            :stat-value="strength"
          />

          <CharacterStatItem
            v-if="isPhysical"
            stat-name="공격력"
            :stat-value="physicalAttack"
          />

          <CharacterStatItem
            v-else
            stat-name="마법 공격력"
            :stat-value="magicAttack"
          />

          <CharacterStatItem
            stat-name="민첩"
            :stat-value="dexterity"
          />

          <CharacterStatItem
            stat-name="방어력"
            :stat-value="defense"
          />

          <CharacterStatItem
            stat-name="지능"
            :stat-value="intelligence"
          />

          <CharacterStatItem
            stat-name="크리티컬"
            :stat-value="critical"
          />

          <CharacterStatItem
            stat-name="의지"
            :stat-value="will"
          />

          <CharacterStatItem
            stat-name="크리티컬 피해량"
            :stat-value="criticalDamage"
          />

          <CharacterStatItem
            stat-name="행운"
            :stat-value="luck"
          />

          <CharacterStatItem
            stat-name="크리티컬 저항"
            :stat-value="criticalResistance"
          />

          <CharacterStatItem
            stat-name="최대 생명력"
            :stat-value="maxHealth"
          />

          <CharacterStatItem
            stat-name="추가피해"
            :stat-value="additionalDamage"
          />

          <CharacterStatItem
            stat-name="최대 스태미나"
            :stat-value="maxStamina"
          />

          <CharacterStatItem
            stat-name="대항력"
            :stat-value="resistance"
          />

          <CharacterStatItem
            stat-name="밸런스"
            :stat-value="balance"
          />
          <CharacterStatItem
            stat-name="공격속도"
            :stat-value="attackSpeed"
          />
          <CharacterStatItem
            stat-name="공격력 제안 해제"
            :stat-value="attackLimitRelease"
          />
        </div>
      </div>
    </div>
  </BaseCard>
</template>

<style scoped></style>
