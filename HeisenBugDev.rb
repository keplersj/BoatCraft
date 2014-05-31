require 'json'
require 'net/http'

manifest = JSON.parse(IO.read('build/libs/manifest.json'))

req = Net::HTTP::Post.new('/builds.json', 'Content-Type' => 'application/json')
req['X-User-Email'] = ENV['HBD_EMAIL_PROD']
req['X-User-Token'] = ENV['HBD_TOKEN_PROD']
req.body = manifest.to_json

Net::HTTP.new('heisenbugdev.com').start do |http|
    http.request(req) do |response|
        json = JSON.parse(response.body)

        puts response.body
        puts response
        
        puts ""
        
        p json
        upload_path = json['upload_path']
        puts upload_path
        puts ""

        req = Net::HTTP::Post.new(upload_path, 'Content-Type' => 'application/json')
        req['X-User-Email'] = ENV['HBD_EMAIL_PROD']
        req['X-User-Token'] = ENV['HBD_TOKEN_PROD']

        req.body =
        '{"file_type":"universal","file":"build/libs/BoatCraft-1.7.2-v2.0.1-100-universal.jar"}'
        
        puts 'heisenbugdev.com' + upload_path
        puts req.body
        puts req
        puts ""

        #if false then
        Net::HTTP.new('heisenbugdev.com').start do |http|
            http.request(req) do |response|
                puts response.body
                puts response
            end
        end
        #end
    end
end
