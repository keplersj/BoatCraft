require 'base64'
require 'json'
require 'net/http'
 
manifest = JSON.parse(IO.read('build/libs/manifest.json'))
 
req = Net::HTTP::Post.new('/builds.json', 'Content-Type' => 'application/json')
req['X-User-Email'] = ENV['HBD_EMAIL_PROD']
req['X-User-Token'] = ENV['HBD_TOKEN_PROD']
req.body = manifest.to_json
 
response = Net::HTTP.new('heisenbugdev.com').start do |http|
  http.request(req)
end

puts response.body['upload_path']
puts response.body
puts response
