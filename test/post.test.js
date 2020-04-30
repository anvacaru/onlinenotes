const chai = require('chai');
const chaiHttp = require('chai-http');
const server = 'http://localhost:8080';
const should = chai.should();
const formData = require('form-data');

chai.use(chaiHttp);
describe('database connect', () => {
    it('db.connection.connect should ...', function(done) {
    db.connection.connect(function(err, result) {
        if(err){
            done(err);
            return;
        }
        expect(result).to.equal("SQL CONNECT SUCCESSFUL.");
        done();
    });
});
});

describe('/POST insert note', () => {
    xit('it should return an error when the request body is missing', (done) => {
        chai.request(server)
                .post('/insert')
                .send({})
                .end((err, res) => {
                    console.log(err)
                    res.should.have.status(200);

                    done();
                });
    });

    it('Should return 200 when inserting an editable, private note', (done) => {
        chai.request(server)
                .post('/insert')
                .field('textarea_content', 'customValue')
                .field('epwd', 'customValue')
                .field('apwd', 'customValue')
                .end((err, res) => {
                    res.should.have.status(200);
                    res.body.should.be.a('object');
                    res.body.should.have.property('isEditable');
                    res.body.should.have.property('isPrivate');
                    res.body.should.have.property('url');
                    res.body.should.have.property('isEditable').eql('true');
                    res.body.should.have.property('isPrivate').eql('true');
                    done();
                });
    });

    xit('Should return 200 when inserting a public note', (done) => {
        chai.request(server)
                .post('/insert')
                .field('textarea_content', 'customValue')
                .field('epwd', '')
                .field('apwd', '')
                .end((err, res) => {
                    res.should.have.status(200);
                    res.body.should.be.a('object');
                    res.body.should.have.property('isEditable');
                    res.body.should.have.property('isPrivate');
                    res.body.should.have.property('url');
                    res.body.should.have.property('isEditable').eql('true');
                    res.body.should.have.property('isPrivate').eql('false');
                    done();
                });
    });
});
