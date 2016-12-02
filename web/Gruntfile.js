module.exports = function (grunt) {

    grunt.initConfig({
       pkg: grunt.file.readJSON('package.json'),

       clean: ['sass/build.scss', 'styles/main.css', 'styles/main.css.map'],

       concat: {
           dist: {
               src: [
                   'sass/*.scss'
               ],
               dest: 'sass/build.scss'
           }
       },

       sass: {
        dist: {
            options: {
                style: 'expanded'
            },
            files: {
                'styles/main.css': 'sass/build.scss'
            }
        }
       },

       autoprefixer: {
        dist: {
            options: {
                diff: false
            },
            src: 'styles/main.css'
        }
       }
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-autoprefixer');

    grunt.registerTask('css', ['clean', 'concat', 'sass', 'autoprefixer']);
};