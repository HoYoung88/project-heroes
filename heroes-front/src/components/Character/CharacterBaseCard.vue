<script setup lang="ts">
  import type { CharacterInfo } from '~/types/Character';
  import { format } from '~/utils/DateTime';

  const { characterInfo } = defineProps<{ characterInfo: CharacterInfo | null }>();

  const isNullCharacterInfo = computed(() => !characterInfo);

  const defaultCharacterInfo = {
    characterName: '',
    characterClassName: '',
    characterGuildName: '',
    characterDateCreate: '',
    characterDateLastLogin: '',
    characterDateLastLogout: '',
    characterLevel: '',
    totalTitleCount: '',
  };

  const mergedCharacterInfo = computed(() => ({
    ...defaultCharacterInfo,
    ...(characterInfo ?? {}),
  }));

  const formattedCharacterDateCreate = computed(() =>
    mergedCharacterInfo.value.characterDateCreate
      ? format(mergedCharacterInfo.value.characterDateCreate)
      : '2022-01-01 이전',
  );

  const formattedCharacterDateLastLogin = computed(() =>
    format(mergedCharacterInfo.value.characterDateLastLogin),
  );

  const formattedCharacterDateLastLogout = computed(() =>
    format(mergedCharacterInfo.value.characterDateLastLogout),
  );

  const { characterClassName, characterName, characterGuildName, characterLevel, totalTitleCount } =
    mergedCharacterInfo.value;
</script>

<template>
  <BaseCard>
    <div
      v-if="isNullCharacterInfo"
      class="rounded-lg bg-red-100 p-4 text-red-800"
      role="alert"
    >
      <span class="block text-sm font-medium max-sm:mt-2 sm:inline">
        캐릭터가 존재하지 않습니다. 검색어를 다시 확인해주세요.
      </span>
    </div>

    <div
      v-else
      class="flex gap-6"
    >
      <div class="flex-shrink-0">
        <CharacterClassAvatar :character-class-name="characterClassName" />
      </div>

      <div class="flex flex-1 flex-col gap-0.5">
        <h2 class="text-xl font-bold text-black">{{ characterName }}</h2>
        <CharacterInfoItem
          label="길드"
          :value="characterGuildName"
        />

        <CharacterInfoItem
          label="생성일"
          :value="formattedCharacterDateCreate"
        />

        <CharacterInfoItem
          label="최근 접속일"
          :value="formattedCharacterDateLastLogin"
        />

        <CharacterInfoItem
          label="최근 종료일"
          :value="formattedCharacterDateLastLogout"
        />

        <CharacterInfoItem
          label="직업"
          :value="characterClassName"
        />

        <CharacterInfoItem
          label="레벨"
          :value="characterLevel"
        />

        <CharacterInfoItem
          label="획득한 타이틀"
          :value="totalTitleCount"
        />
      </div>
    </div>
  </BaseCard>
</template>

<style scoped></style>
