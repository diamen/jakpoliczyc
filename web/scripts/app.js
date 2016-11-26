angular.module('jakPoliczycApp',
    [
        'jakPoliczycControllers',
        'jakPoliczycDirectives',

        'ngMockE2E',

        'AngularAOP',
        'ui.bootstrap',
        'ui.router'
    ])

.constant('mockTags',
    [ 'Funkcje', 'Algebra', 'Bryły', 'Analiza', 'Geometria', 'Analityka', 'Trygonometria',
        'Prawdopodobieństwo', 'Kombinatoryka', 'Topologia', 'Stereometria', 'Wielomiany', 'Logarytmy',
        'Całki', 'Pochodne', 'Tales', 'Pitagoras', 'Równania' ]
)

.constant('mockArticles',

[
    {
        "id": 1,
        "title": "Carlson Dalton",
        "content": "Non fugiat in elit amet nulla fugiat aliquip enim. Culpa aliquip amet deserunt mollit fugiat in irure esse velit occaecat. Consectetur minim aute enim deserunt in minim ad culpa aliquip duis aliqua.\r\n",
        "registered": 1480167949,
        "tags": [
            "Bryły",
            "Analiza",
            "Równania"
        ]
    },
    {
        "id": 2,
        "title": "Wise Watson",
        "content": "Nulla Lorem magna deserunt nisi nulla aliquip voluptate tempor sit. Pariatur laboris enim eu pariatur aute veniam officia amet velit ut magna sit. Ea in voluptate do in sunt duis sit exercitation labore.\r\n",
        "registered": 1480167949,
        "tags": [
            "Stereometria",
            "Logarytmy",
            "Wielomiany"
        ]
    },
    {
        "id": 3,
        "title": "Edwina Rosa",
        "content": "Consectetur fugiat cupidatat minim mollit anim pariatur qui labore ea ea sit commodo. Sint labore veniam mollit amet dolor officia cupidatat consequat esse sint pariatur culpa sit. Nulla adipisicing non non esse elit reprehenderit consectetur laborum. Sunt veniam occaecat ad tempor officia quis.\r\n",
        "registered": 1480167949,
        "tags": [
            "Algebra",
            "Analiza",
            "Całki"
        ]
    },
    {
        "id": 4,
        "title": "Gwendolyn Owen",
        "content": "Proident occaecat proident aliquip ut pariatur esse veniam. Enim mollit adipisicing excepteur minim aliqua sint aliquip quis consectetur elit non voluptate. Nisi reprehenderit duis Lorem eiusmod enim adipisicing deserunt consequat consectetur quis. Do non laboris consectetur Lorem quis pariatur nostrud nulla esse ut consequat. Ad minim occaecat irure cillum dolore aliqua cillum nostrud ut. Nostrud in aliqua exercitation veniam velit aute dolor sunt mollit eu dolor consectetur.\r\n",
        "registered": 1480167949,
        "tags": [
            "Funkcje",
            "Kombinatoryka"
        ]
    },
    {
        "id": 5,
        "title": "Puckett Byrd",
        "content": "Duis voluptate excepteur elit non cillum qui veniam ullamco ut elit veniam mollit fugiat eu. Aute ipsum ut incididunt duis laborum reprehenderit ullamco deserunt in voluptate aute. Consectetur et mollit cupidatat dolor tempor magna.\r\n",
        "registered": 1480167949,
        "tags": [

        ]
    },
    {
        "id": 6,
        "title": "Stanley Herman",
        "content": "Ex eu cillum aute ad Lorem labore culpa ipsum eiusmod nostrud. Mollit excepteur anim magna eu nostrud sunt sit proident. Ea id aliquip esse veniam non officia irure aute ad duis occaecat fugiat. Cupidatat excepteur excepteur labore qui anim. Magna culpa exercitation mollit duis nostrud ex duis.\r\n",
        "registered": 1480167949,
        "tags": [
            "Pitagoras"
        ]
    }
])

.run(function ($httpBackend, mockTags, mockArticles) {
    $httpBackend.whenGET('/tags').respond(mockTags);
    $httpBackend.whenGET('/articles').respond(mockArticles);
    $httpBackend.whenGET(new RegExp('.*')).passThrough();
});