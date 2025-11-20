import { StateCreator } from 'zustand';
import { AppStore } from '../types';

export type TabType = 'home' | 'search' | 'profile' | 'settings';

export interface UIState {
  loading: boolean;
  error: string | null;
  searchInput: string;
  selectedTab: TabType;
  sidebarOpen: boolean;
}

export interface UIActions {
  setLoading: (loading: boolean) => void;
  setError: (error: string | null) => void;
  setSearchInput: (input: string) => void;
  setSelectedTab: (tab: TabType) => void;
  setSidebarOpen: (open: boolean) => void;
  toggleSidebar: () => void;
  clearError: () => void;
}

export interface UISlice extends UIState, UIActions {}

export const createUISlice: StateCreator<
  AppStore,
  [],
  [],
  UISlice
> = (set) => ({
  // 초기 상태
  loading: false,
  error: null,
  searchInput: '',
  selectedTab: 'home',
  sidebarOpen: false,

  // 액션
  setLoading: (loading) => set({ loading }, false, 'setLoading'),
  setError: (error) => set({ error }, false, 'setError'),
  setSearchInput: (searchInput) =>
    set({ searchInput }, false, 'setSearchInput'),
  setSelectedTab: (selectedTab) =>
    set({ selectedTab }, false, 'setSelectedTab'),
  setSidebarOpen: (sidebarOpen) =>
    set({ sidebarOpen }, false, 'setSidebarOpen'),
  toggleSidebar: () =>
    set((state) => ({ sidebarOpen: !state.sidebarOpen }), false, 'toggleSidebar'),
  clearError: () => set({ error: null }, false, 'clearError'),
});

