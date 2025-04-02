<script setup lang="ts">
  const tabList = ['장비', '스킬'];
  const route = useRoute();
  const characterName = ref<string>(route.params.characterName as string);

  const { characterInfo } = await useCharacterInfo(characterName);

  const characterClassName = computed(() =>
    !characterInfo.value ? '' : characterInfo.value.characterClassName,
  );
  const characterStats = computed(() => (!characterInfo.value ? null : characterInfo.value.stat));
  const dressPoint = computed(() => (!characterInfo.value ? null : characterInfo.value.dressPoint));
  const itemEquipments = computed(() =>
    !characterInfo.value ? [] : characterInfo.value.itemEquipments,
  );
  const skillAwakenings = computed(() =>
    !characterInfo.value ? [] : characterInfo.value.skillAwakenings,
  );
</script>

<template>
  <div class="grid grid-cols-1 items-start gap-4 lg:grid-cols-3 lg:gap-8">
    <div class="grid grid-cols-1 gap-4 lg:col-span-1">
      <CharacterBaseCard :character-info="characterInfo" />

      <CharacterStatsCard
        :stat="characterStats"
        :character-class-name="characterClassName"
      />

      <CharacterDressPointCard :dress-point="dressPoint" />
    </div>
    <div class="grid grid-cols-1 gap-4 lg:col-span-2">
      <HeadlessTabGroup
        as="div"
        class=""
      >
        <HeadlessTabList
          as="div"
          class="flex justify-center space-x-2 rounded bg-white p-2 shadow"
        >
          <HeadlessTab
            v-for="(tabName, index) in tabList"
            v-slot="{ selected }"
            :key="index"
            as="template"
          >
            <button
              :class="[
                'cursor-pointer rounded px-4 py-2 text-gray-400 hover:bg-gray-600 hover:text-white',
                selected ? 'bg-gray-600 text-white' : '',
              ]"
            >
              {{ tabName }}
            </button>
          </HeadlessTab>
        </HeadlessTabList>
        <HeadlessTabPanels
          class="mt-2"
          selected-index="0"
        >
          <ItemEquipmentTabPanel :item-equipments="itemEquipments" />
          <SkillAwakeningTabPanel
            :skill-awakening-list="skillAwakenings"
            :character-class-name="characterClassName"
          />
        </HeadlessTabPanels>
      </HeadlessTabGroup>
    </div>
  </div>
</template>

<style scoped></style>
