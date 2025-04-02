<script setup lang="ts">
  const { statName, statValue } = defineProps<{ statName: string; statValue: number }>();
</script>

<template>
  <div class="flex flex-col">
    <p class="text-sm text-gray-600">{{ statName }}</p>
    <p class="text-2xl font-bold text-black">{{ statValue }}</p>
  </div>
</template>

<style scoped></style>
