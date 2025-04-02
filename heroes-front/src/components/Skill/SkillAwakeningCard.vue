<script setup lang="ts">
  import type { SkillAwakening } from '~/types/Character';

  const { skillAwakening } = defineProps<{ skillAwakening: SkillAwakening }>();
  const { skillName, itemName } = skillAwakening;
</script>

<template>
  <div
    class="flex items-center rounded-lg border border-gray-300 p-6 focus-within:ring-2 focus-within:ring-indigo-500 focus-within:ring-offset-2 hover:border-gray-400"
  >
    <div class="flex-shrink-0">
      <SkillAwakeningAvater />
    </div>
    <div class="ms-2 min-w-0 flex-1 text-sm">
      <h2 class="text-sm font-bold text-gray-900">{{ skillName }}</h2>
      <p class="text-gray-500">{{ itemName }}</p>
    </div>
  </div>
</template>

<style scoped></style>
