<script setup lang="ts">
  import type { DressPoint } from '~/types/Character';

  const { dressPoint } = defineProps<{ dressPoint: DressPoint | null }>();

  const defaultDressPoint = {
    totalPoint: 0,
    avatarPoint: 0,
    backPoint: 0,
    tailPoint: 0,
    objectPoint: 0,
  };

  const mergedDressPoint = computed(() => ({
    ...defaultDressPoint,
    ...(dressPoint ?? {}),
  }));

  const { totalPoint, avatarPoint, backPoint, tailPoint, objectPoint } = mergedDressPoint.value;
</script>

<template>
  <BaseCard>
    <div class="flex flex-col gap-0.5">
      <h2 class="text-xl font-bold text-black">드레스 수집 포인트: {{ totalPoint }}</h2>
      <CharacterInfoItem
        label="아바타 수집 포인트"
        :value="avatarPoint"
      />
      <CharacterInfoItem
        label="등 수집 포인트"
        :value="backPoint"
      />
      <CharacterInfoItem
        label="꼬리 수집 포인트"
        :value="tailPoint"
      />
      <CharacterInfoItem
        label="오브젝트 수집 포인트"
        :value="objectPoint"
      />
    </div>
  </BaseCard>
</template>

<style scoped></style>
