"use client";

import React, { lazy, Suspense, useMemo } from 'react';
import { MainLayout } from '../components/templates/MainLayout';
import { useHomePage } from '../hooks/useHomePage';

// Dynamic imports로 코드 스플리팅 (필요한 컴포넌트만 로드)
const DiaryView = lazy(() => import('../components/organisms/DiaryView').then(m => ({ default: m.DiaryView })));
const CalendarView = lazy(() => import('../components/organisms/CalendarView').then(m => ({ default: m.CalendarView })));
const AccountView = lazy(() => import('../components/organisms/AccountView').then(m => ({ default: m.AccountView })));
const CultureView = lazy(() => import('../components/organisms/CultureView').then(m => ({ default: m.CultureView })));
const HealthView = lazy(() => import('../components/organisms/HealthView').then(m => ({ default: m.HealthView })));
const PathfinderView = lazy(() => import('../components/organisms/PathfinderView').then(m => ({ default: m.PathfinderView })));

// 로딩 컴포넌트 (최소화)
const LoadingFallback = () => (
  <div className="flex items-center justify-center h-full">
    <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-gray-900"></div>
  </div>
);

export const HomePage: React.FC = () => {
  const hookData = useHomePage();

  const {
    currentCategory,
    diaryView,
    setDiaryView,
    accountView,
    setAccountView,
    cultureView,
    setCultureView,
    healthView,
    setHealthView,
    pathfinderView,
    setPathfinderView,
    selectedDate,
    setSelectedDate,
    currentMonth,
    setCurrentMonth,
    events,
    setEvents,
    todayTasks,
    setTodayTasks,
    darkMode,
  } = hookData;

  // 카테고리별 컴포넌트를 메모이제이션하여 불필요한 재생성 방지
  const categoryContent = useMemo(() => {
    switch (currentCategory) {
      case 'diary':
        return (
          <DiaryView
            diaryView={diaryView}
            setDiaryView={setDiaryView}
            darkMode={darkMode}
          />
        );
      case 'calendar':
        return (
          <CalendarView
            selectedDate={selectedDate}
            setSelectedDate={setSelectedDate}
            currentMonth={currentMonth}
            setCurrentMonth={setCurrentMonth}
            events={events}
            setEvents={setEvents}
            todayTasks={todayTasks}
            setTodayTasks={setTodayTasks}
            darkMode={darkMode}
          />
        );
      case 'account':
        return (
          <AccountView
            accountView={accountView}
            setAccountView={setAccountView}
            darkMode={darkMode}
          />
        );
      case 'culture':
        return (
          <CultureView
            cultureView={cultureView}
            setCultureView={setCultureView}
            darkMode={darkMode}
          />
        );
      case 'health':
        return (
          <HealthView
            healthView={healthView}
            setHealthView={setHealthView}
            darkMode={darkMode}
          />
        );
      case 'path':
        return (
          <PathfinderView
            pathfinderView={pathfinderView}
            setPathfinderView={setPathfinderView}
            darkMode={darkMode}
          />
        );
      default:
        return null;
    }
  }, [
    currentCategory,
    diaryView,
    setDiaryView,
    accountView,
    setAccountView,
    cultureView,
    setCultureView,
    healthView,
    setHealthView,
    pathfinderView,
    setPathfinderView,
    selectedDate,
    setSelectedDate,
    currentMonth,
    setCurrentMonth,
    events,
    setEvents,
    todayTasks,
    setTodayTasks,
    darkMode,
  ]);

  return (
    <MainLayout {...hookData}>
      <Suspense fallback={<LoadingFallback />}>
        {categoryContent}
      </Suspense>
    </MainLayout>
  );
};

