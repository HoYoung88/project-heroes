<script setup lang="ts">
  import type { ItemEquipment } from '~/types/Character';

  const { itemEquipments } = defineProps<{ itemEquipments: ItemEquipment[] }>();
  const { $meta } = useNuxtApp();
  const slotOrders = $meta.equipment.slotOrders;

  const bagItemEquipments = itemEquipments
    .filter((item) => item.itemEquipmentPage === 'Bag')
    .sort(
      (a, b) =>
        slotOrders.indexOf(a.itemEquipmentSlotName) - slotOrders.indexOf(b.itemEquipmentSlotName),
    );

  const isShowItemEquipmentCard = computed(() => itemEquipments.length > 0);
</script>

<template>
  <HeadlessTabPanel class="rounded bg-white p-6 shadow">
    <div v-if="!isShowItemEquipmentCard">장비 정보가 없습니다.</div>
    <div
      v-else
      class="grid grid-cols-3 gap-4"
    >
      <ItemEquipmentCard
        v-for="(item, index) in bagItemEquipments"
        :key="index"
        :item-equipment="item"
      />
    </div>
  </HeadlessTabPanel>
</template>

<style scoped></style>
