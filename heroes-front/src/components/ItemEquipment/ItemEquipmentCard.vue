<script setup lang="ts">
  import type { ItemEquipment } from '~/types/Character';

  const { $meta } = useNuxtApp();
  const { itemEquipment } = defineProps<{ itemEquipment: ItemEquipment }>();
  const computedItemEquipment = computed(() => ({
    itemEquipmentSlotName: itemEquipment.itemEquipmentSlotName,
    itemName: itemEquipment.itemName,
    itemOption: itemEquipment.itemOption,
  }));

  const { itemEquipmentSlotName, itemName, itemOption } = computedItemEquipment.value;

  const {
    enhancementLevel,
    tuningStats,
    prefixEnchantPreset,
    suffixEnchantPreset,
    powerInfusionPresetStatName,
    powerInfusionPresetStatValue,
  } = itemOption;
  const txtSlotName = computed(() => $meta.equipment.slotName[itemEquipmentSlotName]);

  const computedEnhancementLevel = computed(() =>
    enhancementLevel > 0 ? '+' + enhancementLevel : '',
  );

  const powerInfusionPreset = computed(() => {
    if (powerInfusionPresetStatName !== null && powerInfusionPresetStatValue !== null) {
      return powerInfusionPresetStatName + ' +' + powerInfusionPresetStatValue;
    } else {
      return '';
    }
  });
</script>

<template>
  <div
    class="flex items-center rounded-lg border border-gray-300 bg-white p-6 focus-within:ring-2 focus-within:ring-indigo-500 focus-within:ring-offset-2 hover:border-gray-400"
    :class="itemEquipmentSlotName === 'LOWER' ? 'col-end-3' : ''"
  >
    <div class="flex-shrink-0">
      <ItemEquipmentAvater />
    </div>
    <div class="ms-2 min-w-0 flex-1 text-sm">
      <p class="text-sm font-medium text-amber-600">
        {{ prefixEnchantPreset }} {{ suffixEnchantPreset }}
      </p>
      <!-- 아이템 이름 -->
      <h2 class="text-sm font-bold text-gray-900">{{ computedEnhancementLevel }} {{ itemName }}</h2>

      <!-- 아이템 타입 -->
      <p class="text-sm text-gray-500">{{ txtSlotName }}</p>

      <p class="text-green-500">
        {{ powerInfusionPreset }}
      </p>

      <p
        v-for="(item, index) in tuningStats"
        :key="index"
        class="text-purple-500"
      >
        {{ item.statName }} +{{ item.statValue }}
      </p>
    </div>
  </div>
</template>

<style scoped></style>
