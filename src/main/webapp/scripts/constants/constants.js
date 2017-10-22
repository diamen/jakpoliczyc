angular.module('jakPoliczycConstants', [])
    .constant('constants', {
        KahootDifficulties: {
            very_easy: {
                name: 'VERY_EASY',
                languageKey: 'levVEasy'
            },
            easy: {
                name: 'EASY',
                languageKey: 'levEasy'
            },
            middle: {
                name: 'MIDDLE',
                languageKey: 'levMiddle'
            },
            difficult: {
                name: 'DIFFICULT',
                languageKey: 'levDiff'
            },
            very_difficult: {
                name: 'VERY_DIFFICULT',
                languageKey: 'levVDiff'
            }
        }
});