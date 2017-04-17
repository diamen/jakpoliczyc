angular.module('jakPoliczycServices')
    .service("jpPdfService", [function () {

        var lMargin = 15; //left margin in mm
        var rMargin = 15; //right margin in mm
        var pdfWidthMM = 210;  // width of A4 in mm
        var pdfHeightMM = 297;

        var pointToMM = function (point) {
            return (point / 72) * 25.4;
        };

        var saveAsPdf = function (content) {
            var pdf = new jsPDF();
            pdf.fromHTML(content, 200, 200, {
                'width': 500
            });
            pdf.save('te.pdf');
        };

        // var saveAsPdf = function (intro, content) {
        //     var text = content.replace(/<br\/>/g, '\n\r');
        //     var pdf = new jsPDF("p", "mm", "a4");
        //     var fontSize = 12;
        //     var defaultFontSize = pdf.internal.getFontSize();
        //     var lines = pdf.splitTextToSize(text, (defaultFontSize / fontSize) * (pdfWidthMM - lMargin - rMargin));
        //     var noOfPages = Math.round((lines.length * pointToMM(fontSize + 1)) / (pdfHeightMM - 2 * 20));
        //     var noOfLinesOnPage = Math.floor((pdfHeightMM - 2 * 20) / pointToMM(fontSize + 1));
        //     console.log(noOfPages);
        //     console.log(noOfLinesOnPage);
        //     pdf.setFontSize(fontSize);
        //
        //     for (var i = 0; i < noOfPages; i++) {
        //         var toPut = _.first(lines, noOfLinesOnPage);
        //         lines = _.rest(lines, noOfLinesOnPage);
        //         pdf.text(pdfWidthMM / 2, 20, toPut, 'center');
        //
        //         if (i + 1 < noOfPages) {
        //             pdf.addPage();
        //         }
        //     }
        //
        //     pdf.save("Test.pdf");
        // };

        return {
            saveAsPdf: saveAsPdf
        }

    }]);