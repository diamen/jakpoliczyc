module.exports = function (grunt) {

    grunt.initConfig({
       pkg: grunt.file.readJSON('package.json'),

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
                diff: true
            },
            src: 'styles/main.css'
        }
       }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-autoprefixer');

    grunt.registerTask('css', ['concat', 'autoprefixer', 'sass']);
};