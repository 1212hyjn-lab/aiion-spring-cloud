import React from 'react';
import { Button } from '../atoms';
import { DiaryView as DiaryViewType } from '../types';

interface DiaryViewProps {
  diaryView: DiaryViewType;
  setDiaryView: (view: DiaryViewType) => void;
  darkMode?: boolean;
}

export const DiaryView: React.FC<DiaryViewProps> = ({
  diaryView,
  setDiaryView,
  darkMode = false,
}) => {
  if (diaryView === 'home') {
    return (
      <div className="flex-1 flex flex-col overflow-hidden">
        <div className="flex-1 overflow-y-auto p-6">
          <div className="max-w-4xl mx-auto space-y-4">
            <div className="text-center py-4">
              <h1 className="text-3xl font-bold text-gray-900">Diary_home</h1>
            </div>

            <div className="bg-white rounded-2xl border-2 border-[#8B7355] p-8 shadow-lg">
              <h2 className="text-2xl font-bold text-gray-900 mb-4 text-center border-b-2 border-[#d4c4a8] pb-3">
                📊 종합감정 분석
              </h2>
              <div className="text-gray-900 leading-relaxed text-sm">
                <p className="text-center text-gray-500 py-4">
                  아직 작성된 일기가 없습니다. 첫 일기를 작성해보세요!
                </p>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-6">
              <Button
                onClick={() => setDiaryView('write')}
                className="bg-gradient-to-br from-white to-[#f5f0e8] rounded-2xl border-2 border-[#8B7355] p-12 hover:shadow-lg hover:scale-105 transition-all duration-200"
              >
                <div className="flex flex-col items-center space-y-3">
                  <span className="text-4xl">✍️</span>
                  <p className="text-2xl font-bold text-gray-900">일기쓰기</p>
                </div>
              </Button>
              <Button
                onClick={() => setDiaryView('list')}
                className="bg-gradient-to-br from-white to-[#f5f0e8] rounded-2xl border-2 border-[#8B7355] p-12 hover:shadow-lg hover:scale-105 transition-all duration-200"
              >
                <div className="flex flex-col items-center space-y-3">
                  <span className="text-4xl">📋</span>
                  <p className="text-2xl font-bold text-gray-900">일기리스트</p>
                </div>
              </Button>
            </div>

            <Button
              onClick={() => setDiaryView('analysis')}
              className="w-full bg-gradient-to-br from-white to-[#f5f0e8] rounded-2xl border-2 border-[#8B7355] p-12 hover:shadow-lg hover:scale-105 transition-all duration-200"
            >
              <div className="flex flex-col items-center space-y-3">
                <span className="text-4xl">📈</span>
                <p className="text-2xl font-bold text-gray-900">감정분석 그래프</p>
              </div>
            </Button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="flex-1 overflow-y-auto p-6">
      <div className="max-w-4xl mx-auto">
        <p className="text-gray-600">Diary view: {diaryView}</p>
      </div>
    </div>
  );
};

