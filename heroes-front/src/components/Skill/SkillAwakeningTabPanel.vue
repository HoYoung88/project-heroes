<script setup lang="ts">
  import type { SkillAwakening } from '~/types/Character';

  const { skillAwakeningList, characterClassName } = defineProps<{
    skillAwakeningList: SkillAwakening[];
    characterClassName: string;
  }>();
  const { $meta } = useNuxtApp();
  const isSecondaryWeaponSkill = $meta.character.skill[characterClassName] ?? [].length > 0;
  const secondaryWeaponSkillList = $meta.character.skill[characterClassName] ?? [];

  const primaryWeaponSkill = skillAwakeningList.filter(
    (skill) => !secondaryWeaponSkillList.includes(skill.skillName),
  );
  const secondaryWeaponSkill = skillAwakeningList.filter((skill) =>
    secondaryWeaponSkillList.includes(skill.skillName),
  );

  const isShowSkillAwakeningCard = computed(() => skillAwakeningList.length > 0);
</script>

<template>
  <HeadlessTabPanel class="rounded bg-white p-6 shadow">
    <div v-if="!isShowSkillAwakeningCard">스킬 정보가 없습니다.</div>
    <div
      v-else
      class="grid grid-cols-2 gap-2"
    >
      <div class="flex flex-col gap-2">
        <h3
          v-if="isSecondaryWeaponSkill"
          class="mb-4 text-center text-lg font-semibold text-black"
        >
          1차 무기 스킬
        </h3>
        <SkillAwakeningCard
          v-for="(item, index) in primaryWeaponSkill"
          :key="index"
          :skill-awakening="item"
        />
      </div>
      <div
        v-if="isSecondaryWeaponSkill"
        class="flex flex-col gap-2"
      >
        <h3 class="mb-4 text-center text-lg font-semibold text-black">2차 무기 스킬</h3>
        <SkillAwakeningCard
          v-for="(item, index) in secondaryWeaponSkill"
          :key="index"
          :skill-awakening="item"
        />
      </div>
    </div>
  </HeadlessTabPanel>
</template>

<style scoped></style>

<!--

-->
