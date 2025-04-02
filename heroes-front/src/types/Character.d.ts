export interface CharacterInfo {
  characterName: string;
  characterGuildName: string;
  characterDateCreate: string | null;
  characterDateLastLogin: string;
  characterDateLastLogout: string;
  characterClassName: string;
  characterGender: string;
  characterExp: number;
  characterLevel: number;
  guildName: string;
  cairdeName: string;
  titleCount: number;
  idTitleCount: number;
  totalTitleCount: number;
  titleStat: TitleStat[];
  skillAwakenings: SkillAwakening[];
  dressPoint: DressPoint;
  stat: Stat;
  itemEquipments: ItemEquipment[];
}

export interface TitleStat {
  statName: string;
  statValue: string;
}

export interface SkillAwakening {
  skillName: string;
  itemName: string;
}

export interface DressPoint {
  totalPoint: number;
  avatarPoint: number;
  backPoint: number;
  tailPoint: number;
  objectPoint: number;
}

export interface Stat {
  physicalAttack: number;
  magicAttack: number;
  defense: number;
  strength: number;
  dexterity: number;
  intelligence: number;
  will: number;
  luck: number;
  maxHealth: number;
  maxStamina: number;
  attackSpeed: number;
  additionalDamage: number;
  critical: number;
  criticalDamage: number;
  criticalResistance: number;
  balance: number;
  attackLimitRelease: number;
  resistance: number;
}

export interface ItemEquipment {
  itemEquipmentPage: 'Bag' | 'Cash';
  itemEquipmentSlotName: string;
  itemName: string;
  itemOption: ItemOption;
}

export interface ItemOption {
  enhancementLevel: number;
  tuningStats: TuningStat[];
  abilityName?: string;
  prefixEnchantPreset?: string;
  suffixEnchantPreset?: string;
  powerInfusionPresetStatName?: string;
  powerInfusionPresetStatValue?: string;
  color1?: string;
  color2?: string;
  color3?: string;
}

export interface TuningStat {
  statName: string;
  statValue: string;
}
